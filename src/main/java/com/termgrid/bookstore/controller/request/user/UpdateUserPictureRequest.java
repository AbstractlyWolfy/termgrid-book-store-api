package com.termgrid.bookstore.controller.request.user;

public class UpdateUserPictureRequest {

    private String profilePicture;

    /**
     * Get users new profile picture
     *
     * @return profile picture - {@link String}
     */
    public String getProfilePicture() {
        return profilePicture;
    }
}
