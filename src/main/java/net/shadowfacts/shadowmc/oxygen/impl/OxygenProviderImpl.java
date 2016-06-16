package net.shadowfacts.shadowmc.oxygen.impl;

import net.shadowfacts.shadowmc.oxygen.OxygenProvider;

/**
 * Default oxygen provider implementation.
 *
 * @author shadowfacts
 */
public class OxygenProviderImpl extends OxygenHandlerImpl implements OxygenProvider {

	/**
	 * Default no-args constructor, used by Forge.
	 */
	public OxygenProviderImpl() {
		super();
	}

	/**
	 * @param capacity The maximum amount of oxygen that can be stored
	 * @param transferRate The maximum amount of oxygen that can be transferred in 1 operation
	 */
	public OxygenProviderImpl(int capacity, int transferRate) {
		super(capacity, transferRate);
	}

	@Override
	public int extract(int amount, boolean simulate) {
		int extracted = Math.min(stored, Math.min(transferRate, amount));

		if (!simulate) stored -= extracted;

		return extracted;
	}

}
