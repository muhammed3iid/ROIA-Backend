package ROIA.IAM.Service.Proxy;

import ROIA.IAM.Model.User;

public interface IAuthentication {
    User proxySignUp(User user) throws Exception;

    User proxySignIn(User user) throws Exception;

}
