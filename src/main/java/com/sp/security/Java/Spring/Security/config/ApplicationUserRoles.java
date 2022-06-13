package com.sp.security.Java.Spring.Security.config;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,ApplicationUserPermission.COURSE_WRITE,ApplicationUserPermission.STUDENT_READ,ApplicationUserPermission.STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,ApplicationUserPermission.STUDENT_READ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
