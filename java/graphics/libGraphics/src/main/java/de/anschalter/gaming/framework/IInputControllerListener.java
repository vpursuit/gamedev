package de.anschalter.gaming.framework;

import java.util.EventListener;

public interface IInputControllerListener extends EventListener{

	public abstract void inputEvent(IControllerEvent ev);
		
}
