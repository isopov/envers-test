package com.sopovs.moradanen.envers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

public class EnvertsTestComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Label label;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		label.setValue("Hello World!");
	}

}
