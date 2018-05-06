package encostai.encostai.com.br.encostaai.activity.profile;


import encostai.encostai.com.br.encostaai.utils.Preferences;

public class ProfilePresenter implements IProfilePresenter, IProfileInteractor.ProfileListener {

    private IProfileView profileView;
    private IProfileInteractor profileInteractor;

    ProfilePresenter(IProfileView profileView, IProfileInteractor profileInteractor) {
        this.profileView = profileView;
        this.profileInteractor = profileInteractor;
    }


    public void changeUserInfo(String name, boolean exposure, String password, String newPassword, String confirmPassword) {

        profileInteractor.changeInfo(name, exposure, password, newPassword, confirmPassword, this, (ProfileActivity) profileView);

    }


    public void onActionResponse(int messageType) {

        switch (messageType) {

            case 0:
                profileView.setMessage("Error: Campo senha em branco");
                break;
            case 1:
                profileView.setMessage("Error: Senha errada");
                break;
            case 2:
                profileView.setMessage("Error: Campo nova senha e cofirmar nova senha são diferentes");
                break;
            case 3:
                profileView.setMessage("Error: Erro ao tentar alterar informações");
                break;
            case 4:
                profileView.setMessage("Informações alteradas com sucesso");
                break;
        }

    }

    public void onSucess() {
        onActionResponse(4);
        profileView.restart();
    }

    public Preferences getUserInfo(ProfileActivity profileActivity) {
        return profileInteractor.getUserInfo(profileActivity);
    }

}
