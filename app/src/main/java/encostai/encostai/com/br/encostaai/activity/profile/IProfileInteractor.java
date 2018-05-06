package encostai.encostai.com.br.encostaai.activity.profile;

import encostai.encostai.com.br.encostaai.utils.Preferences;

public interface IProfileInteractor {

    interface ProfileListener {

        void onActionResponse(int messageType);

        void onSucess();
    }

    void changeInfo(String name, boolean exposure, String password, String newPassword, String confirmPassword, ProfileListener profileListener, ProfileActivity profileActivity);

    Preferences getUserInfo(ProfileActivity profileActivity);
}
