package org.fatec.listener;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.fatec.model.Usuario;
import org.slf4j.Logger;

public class LoginListener {

	@Inject
	private Logger logger;

	public void onLogin(@Observes Usuario usuario) {
		logger.info("Usuario %s se logou no sistema. ", usuario.getEmail());
	}
}
