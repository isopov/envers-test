package com.sopovs.moradanen.bouquinist;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;

import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

public class PersonComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository;

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

		personRepository = SpringUtil.getApplicationContext().getBean(PersonRepository.class);
		Long personId = (Long) Executions.getCurrent().getArg().get("person-id");
		Person person = personRepository.getOne(personId);

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
