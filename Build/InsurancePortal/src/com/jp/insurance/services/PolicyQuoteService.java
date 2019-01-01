package com.jp.insurance.services;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jp.insurance.daos.VehicleMasterDao;
import com.jp.insurance.daos.interfaces.IVehicleMasterDao;
import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyQuoteService;

@Service("policyQuoteService")
public class PolicyQuoteService implements Serializable, IPolicyQuoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5585541658562669080L;

	private IVehicleMasterDao vehicleMasterDao;

	public PolicyQuoteService() throws InsuranceException {

	}

	@Autowired
	public PolicyQuoteService(@Qualifier("vehicleMasterDao") VehicleMasterDao vehicleMasterDao)
			throws InsuranceException {
		this.vehicleMasterDao = vehicleMasterDao;
	}

	public Float getPolicyPremium(CustomerVehicle customerVehicle) throws InsuranceException {

		Float policyPremium=0F;
		Float marketPrice = vehicleMasterDao.getVehiclePriceByMakeModelSubmodel(customerVehicle.getMake(),
				customerVehicle.getModel(), customerVehicle.getSubModel());
		Float depreciationPercentage = getDepreciationPercentage(customerVehicle.getManufacturingYear());

		Float Idv = marketPrice * (depreciationPercentage / 100);
		
		Float totalODPremium = (float) (Idv * 0.0197);
		
		Integer personalAccidentCover = 100;
		Integer legalLiabilityPaidToDriver = 50;
		Integer compulsoryThirdPartyCover = 1110;
		
		Float netPremium = totalODPremium + personalAccidentCover+legalLiabilityPaidToDriver+compulsoryThirdPartyCover;
		
		Double GST = 0.18;
		
		policyPremium = (float) (netPremium*(1+GST));
		
		return policyPremium;

	}

	private Float getDepreciationPercentage(Integer manufacturingYear) {
		Float depreciationPercentage = 0F;
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer noOfYears = currentYear - manufacturingYear;
		switch (noOfYears) {
		case 0: {
			depreciationPercentage = 0F;
			break;
		}
		case 1: {
			depreciationPercentage = 20F;
			break;
		}
		case 2: {
			depreciationPercentage = 30F;
			break;
		}
		case 3: {
			depreciationPercentage = 40F;
			break;
		}
		case 4: {
			depreciationPercentage = 50F;
			break;
		}
		default: {
			depreciationPercentage = 50F;
			break;
		}
		}

		return depreciationPercentage;

	}

}
