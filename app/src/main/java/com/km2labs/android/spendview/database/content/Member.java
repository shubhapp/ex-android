/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.km2labs.android.spendview.database.content;


@org.parceler.Parcel(value = org.parceler.Parcel.Serialization.BEAN)
public class Member {

    private long id;

    private String memberName;

    private String memberEmail;

    private String memberPhoneNumber;

    private String coverPhotoUrl;

    private String profilePhotoUrl;

    private boolean isRegistered;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    @Override
    public int hashCode() {
        int result = getMemberName().hashCode();
        result = 31 * result + getMemberEmail().hashCode();
        result = 31 * result + getCoverPhotoUrl().hashCode();
        result = 31 * result + getProfilePhotoUrl().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Member member = (Member) o;
        if (member.getMemberEmail().equalsIgnoreCase(getMemberEmail()) || member.getMemberPhoneNumber() == getMemberPhoneNumber()) {
            return true;
        }
        return false;
    }
}
