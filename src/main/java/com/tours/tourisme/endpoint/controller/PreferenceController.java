package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.repository.entity.Preference;
import com.tours.tourisme.service.PreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PreferenceController {
    private final PreferenceService preferenceService;

    @PostMapping("/users/{uid}/preferences")
    public Preference savePreference(@PathVariable Long uid,
                                     @RequestBody Preference preference){
        return preferenceService.savePreference(uid, preference);
    }

    @PutMapping("/users/{uid}/preferences/{pid}")
    public Preference crupdatePreference(@PathVariable Long uid,
                                         @PathVariable Long pid,
                                         @RequestBody Preference preference){
        return preferenceService.cruptadePreference(uid, pid, preference);
    }
}
