package com.jerry.savior_user.constants;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Set;

/**
 * @author 22454
 */
public interface EnumTypeReference {
    TypeReference<Set<Long>> LONG_SET_TYPE_REFERENCE = new TypeReference<Set<Long>>() {
    };
}
