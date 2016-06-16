package net.shadowfacts.shadowmc.oxygen.impl;

import net.shadowfacts.shadowmc.oxygen.OxygenProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;

import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class OxygenTank extends OxygenHandlerImpl implements OxygenProvider, OxygenReceiver {

	private Consumer<OxygenTank> updateHandler;

	/**
	 * @param capacity The maximum amount of oxygen that can be stored
	 * @param transferRate The maximum amount of oxygen that can be transferred in 1 operation
	 * @param updateHandler A handler that is called whenever a change in the amount of oxygen occurs
	 */
	public OxygenTank(int capacity, int transferRate, Consumer<OxygenTank> updateHandler) {
		super(capacity, transferRate);
		this.updateHandler = updateHandler;
	}

	@Override
	public int receive(int amount, boolean simulate) {
		int received = Math.min(capacity - stored, Math.min(transferRate, amount));

		if (!simulate) {
			stored += received;
			if (updateHandler != null) updateHandler.accept(this);
		}

		return received;
	}

	@Override
	public int extract(int amount, boolean simulate) {
		int extracted = Math.min(stored, Math.min(transferRate, amount));

		if (!simulate) {
			stored -= extracted;
			if (updateHandler != null) updateHandler.accept(this);
		}

		return extracted;
	}

}
