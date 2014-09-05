package com.sopovs.moradanen.bouquinist;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

public class MainBouquinistComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Window contentWindow;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		contentWindow.setTitle("Authors");
		contentWindow.appendChild(Executions.createComponents("/personList.zul", comp, null));
	}

}
