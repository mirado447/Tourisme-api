package com.tours.tourisme.service;

import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.PreferenceRepository;
import com.tours.tourisme.repository.entity.Preference;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PreferenceService {
    private final PreferenceRepository repository;
    private final LocationService locationService;
    private final UserService userService;

    public Preference savePreference(Long uid, Preference preference){
        preference.setStart_location(locationService.getLocationById(preference.getStart_location().getId()));
        preference.setEnd_location(locationService.getLocationById(preference.getEnd_location().getId()));
        preference.setUser(userService.getUserById(uid));
        return repository.save(preference);
    }

    public Preference getPreferenceById(Long pid){
        return repository.findById(pid)
                .orElseThrow(() -> new NotFoundException("Preference with id "+ pid + " not found"));
    }

    public Preference cruptadePreference(Long uid, Long pid, Preference preference){
        Optional<Preference> optionalPreference = repository.findById(pid);
        if(optionalPreference.isPresent()){
            Preference preferenceFromDomain = optionalPreference.get();
            preference.setUser(preferenceFromDomain.getUser());
            preference.setId(pid);
            return repository.save(preference);
        }else {
            return savePreference(uid, preference);
        }
    }
}
