package forms;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

import dao.DAOUser;
import dao.IDAOUser;
import domain.User;

public class LoginValidation {

	private IDAOUser daoUser;
	
	public LoginValidation(IDAOUser daoUser) {
		this.daoUser = daoUser;
	}
	
	/**
	 * Permet de déterminer si un couple login - mot de passe est valide.
	 * @param login un login
	 * @param password un mot de passe
	 * @return true si le couple login - mot de passe est valide et false sinon.
	 */
	public boolean isValid(String login, String password) {
		if(daoUser instanceof DAOUser) {
			User user = daoUser.find(login, password);
			if(user != null) {
			    
			    String channelKey = login;
                ChannelService channelService = ChannelServiceFactory.getChannelService();
                // Creation dun Channel en utilisant le channelKey recu du client
                String chatToken = channelService.createChannel(channelKey);
                user.setChatToken(chatToken);
                
                user.setConnected(true);
                daoUser.update(user);
			    
				return true;
			}
		}
		return false;
	}
	
}
