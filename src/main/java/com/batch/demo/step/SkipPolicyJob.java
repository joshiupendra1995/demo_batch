package com.batch.demo.step;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class SkipPolicyJob implements SkipPolicy {

	@Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {

		if (skipCount <= 1) {
			return true;
		}
		return false;
	}

}
