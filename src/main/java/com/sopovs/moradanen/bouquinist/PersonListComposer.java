package com.sopovs.moradanen.bouquinist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

public class PersonListComposer extends GenericForwardComposer<Listbox> {
	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository;

	@Override
	public void doAfterCompose(Listbox comp) throws Exception {
		super.doAfterCompose(comp);

		personRepository = SpringUtil.getApplicationContext().getBean(PersonRepository.class);
		comp.setModel(new PersonListModel(comp.getPageSize()));
		comp.setItemRenderer(new ListitemRenderer<Person>() {
			@Override
			public void render(Listitem item, Person person, int index) throws Exception {
				item.setTooltip("personPopup");
				item.setValue(person.getId());
				new Listcell(person.getId().toString()).setParent(item);
				new Listcell(person.getFirstName()).setParent(item);
				new Listcell(person.getSecondName()).setParent(item);
				new Listcell(person.getLastName()).setParent(item);
			}
		});
	}

	private class PersonListModel extends AbstractListModel<Person> {

		private final int pageSize;
		private Page<Person> page;

		public PersonListModel(int pageSize) {
			this.pageSize = pageSize;
		}

		@Override
		public Person getElementAt(int index) {
			int pageNumber = index / pageSize;
			if (page == null || pageNumber != page.getNumber()) {
				page = personRepository.findAll(new PageRequest(pageNumber, pageSize, Person.DEFAULT_SORT));
			}
			return page.getContent().get(index % pageSize);
		}

		@Override
		public int getSize() {
			return (int) personRepository.count();
		}
	}
}
