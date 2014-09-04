package com.sopovs.moradanen.bouquinist;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;

import com.sopovs.moradanen.bouquinist.dto.PersonDetailsDTO;
import com.sopovs.moradanen.bouquinist.services.BouquinistService;

public class PersonComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 1L;

	public static final String PERSON_ID = "person-id";

	private BouquinistService bouquinistService;

	@Wire
	private Label firstname;
	@Wire
	private Label secondname;
	@Wire
	private Label secondnameLabel;
	@Wire
	private Label lastname;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		bouquinistService = SpringUtil.getApplicationContext().getBean(BouquinistService.class);
		Long personId = (Long) Executions.getCurrent().getArg().get(PERSON_ID);
		PersonDetailsDTO person = bouquinistService.getPersonDetails(personId);

		firstname.setValue(person.getFirstName());
		if (person.getSecondName() == null) {
			secondname.setVisible(false);
			secondnameLabel.setVisible(false);
		} else {
			secondname.setValue(person.getSecondName());
		}
		lastname.setValue(person.getLastName());
	}

}
