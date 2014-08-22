package com.sopovs.moradanen.bouquinist;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;

import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

public class MainBouquinistComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Label label;

	private PersonRepository personRepository;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		personRepository = SpringUtil.getApplicationContext().getBean(PersonRepository.class);
		super.doAfterCompose(comp);
		label.setValue("Всего авторов - " + personRepository.count());
	}

}
