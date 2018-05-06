package encostai.encostai.com.br.encostaai.activity.profile;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import encostai.encostai.com.br.encostaai.models.User;
import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;
import encostai.encostai.com.br.encostaai.utils.Preferences;

public class ProfileInteractor implements IProfileInteractor {


    private FirebaseAuth firebaseAuth;
    private User user;
    private Preferences mPreferences;

    ProfileInteractor() {
        firebaseAuth = FirebaseConfig.getFirebaseAuth();
        user = new User();
    }


    public void changeInfo(final String name, final boolean exposure, final String password, final String newPassword, final String confirmPassword, final ProfileListener listener, final ProfileActivity profileActivity) {

        if (!password.isEmpty()) {


            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), password);
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        //alterar apenas o exposure
                        if (name.isEmpty() && newPassword.isEmpty()) {
                            user.setName(mPreferences.getName());
                            user.setId(mPreferences.getIdentifier());
                            user.setEmail(mPreferences.getEmail());
                            user.setExposure(exposure);
                            user.save();
                            mPreferences.saveData(user.getId(), user.getName(), user.getEmail());
                            listener.onSucess();
                        } else if (!name.isEmpty() && !newPassword.isEmpty()) {
                            //alterar senha, nome e exposure
                            user.setName(name);
                            user.setId(mPreferences.getIdentifier());
                            user.setEmail(mPreferences.getEmail());
                            user.setExposure(exposure);
                            if (checkPassword(newPassword, confirmPassword, listener)) {

                                FirebaseUser userF = firebaseAuth.getCurrentUser();
                                userF.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            user.save();
                                            mPreferences.saveData(user.getId(), user.getName(), user.getEmail());
                                            listener.onSucess();
                                        } else {
                                            listener.onActionResponse(3);
                                        }
                                    }
                                });

                            }
                        } else if (newPassword.isEmpty()) {
                            //alterar nome e exposure
                            user.setName(name);
                            user.setId(mPreferences.getIdentifier());
                            user.setEmail(mPreferences.getEmail());
                            user.setExposure(exposure);
                            user.save();
                            mPreferences.saveData(user.getId(), user.getName(), user.getEmail());
                            listener.onSucess();
                        } else {
                            //alterar senha e exposure
                            if (checkPassword(newPassword, confirmPassword, listener)) {
                                user.setName(mPreferences.getName());
                                user.setId(mPreferences.getIdentifier());
                                user.setEmail(mPreferences.getEmail());
                                user.setExposure(exposure);

                                FirebaseUser userF = firebaseAuth.getCurrentUser();
                                userF.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            user.save();
                                            mPreferences.saveData(user.getId(), user.getName(), user.getEmail());
                                            listener.onSucess();
                                        } else {
                                            listener.onActionResponse(3);
                                        }


                                    }
                                });


                            }
                        }


                    } else {
                        listener.onActionResponse(1);
                    }


                }
            });

        } else {
            listener.onActionResponse(0);
        }
    }


    private boolean checkPassword(String newPassword, String confirmPassword, ProfileListener listener) {

        if (newPassword.equals(confirmPassword)) {
            return true;
        } else {
            listener.onActionResponse(2);
            return false;
        }
    }


    public Preferences getUserInfo(ProfileActivity profileActivity) {
        this.mPreferences = new Preferences(profileActivity);

        return mPreferences;
    }

}
