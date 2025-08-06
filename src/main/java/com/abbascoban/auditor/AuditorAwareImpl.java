package com.abbascoban.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    //@CreatedBy ve @LastModifiedBy için tanımlanması gerekiyor
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Abbas");
    }
}
