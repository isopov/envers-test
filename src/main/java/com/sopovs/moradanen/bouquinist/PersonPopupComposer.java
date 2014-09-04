package com.sopovs.moradanen.bouquinist;

import static com.sopovs.moradanen.bouquinist.PersonComposer.PERSON_ID;
import static java.util.Collections.singletonMap;
import static org.zkoss.zk.ui.Executions.createComponents;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;

import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

public class PersonPopupComposer extends GenericForwardComposer<Popup> {
	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository;

	@Override
	public void doAfterCompose(final Popup comp) throws Exception {
		super.doAfterCompose(comp);
		personRepository = SpringUtil.getApplicationContext().getBean(PersonRepository.class);

		comp.addEventListener(Events.ON_OPEN, new EventListener<OpenEvent>() {

			@Override
			public void onEvent(OpenEvent event) throws Exception {
				comp.getChildren().clear();
				Component ref = event.getReference();
				if (ref instanceof Listitem) {
					Long personId = ((Listitem) ref).getValue();
					Component child = createComponents("/person.zul", comp, singletonMap(PERSON_ID, personId));
					comp.appendChild(child);
				}
			}
		});
	}
}
