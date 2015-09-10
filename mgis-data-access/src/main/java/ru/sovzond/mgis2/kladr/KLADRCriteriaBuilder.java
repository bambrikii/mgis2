package ru.sovzond.mgis2.kladr;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Alexander Arakelyan on 10.09.15.
 */
public class KLADRCriteriaBuilder {
	public static final String SUBJECT_TAIL = "00000000000";
	public static final String REGION_TAIL = "00000000";
	public static final String SUBJECT_ANY = "___________";
	public static final String REGION_ANY = "________";
	public static final String CITY_ANY = "___";
	private String subjectCode;
	private String regionCode;
	private String localityCode;

	private boolean isFilled(String str) {
		return str != null && str.length() > 0;
	}

	public KLADRCriteriaBuilder localityCode(String localityCode) {
		this.localityCode = localityCode;
		return this;
	}

	public KLADRCriteriaBuilder regionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public KLADRCriteriaBuilder subjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
		return this;
	}

	//

	public void filterSubject(Criteria criteria, String subjectName) {
		if (isFilled(localityCode)) {
			criteria.add(Restrictions.like("code", localityCode.substring(0, 2) + SUBJECT_TAIL));
		} else if (isFilled(regionCode)) {
			criteria.add(Restrictions.eq("code", regionCode.substring(0, 2) + SUBJECT_TAIL));
		} else if (isFilled(subjectCode)) {
			criteria.add(Restrictions.eq("code", subjectCode));
		} else {
			criteria.add(Restrictions.like("code", "__" + SUBJECT_TAIL));
		}
		if (subjectName != null && subjectName.length() > 0) {
			criteria.add(Restrictions.like("name", "%" + subjectName + "%"));
		}
	}

	public void filterRegion(Criteria criteria, String regionName) {
		if (isFilled(localityCode)) {
			criteria.add(Restrictions.like("code", localityCode.substring(0, 5) + REGION_TAIL));
		} else if (isFilled(regionCode)) {
			criteria.add(Restrictions.eq("code", regionCode));
		} else if (isFilled(subjectCode)) {
			criteria.add(Restrictions.like("code", subjectCode.substring(0, 2) + CITY_ANY + REGION_TAIL));
		}
		if (regionName != null && regionName.length() > 0) {
			criteria.add(Restrictions.like("name", "%" + regionName + "%"));
		}
	}

	public void filterLocality(Criteria criteria, String localityName) {
		if (isFilled(localityCode)) {
			criteria.add(Restrictions.eq("code", localityCode));
		} else if (isFilled(regionCode)) {
			criteria.add(Restrictions.like("code", regionCode.substring(0, 5) + REGION_ANY));
		} else if (isFilled(subjectCode)) {
			criteria.add(Restrictions.like("code", subjectCode.substring(0, 2) + SUBJECT_ANY));
		}
		if (localityName != null && localityName.length() > 0) {
			criteria.add(Restrictions.like("name", "%" + localityName + "%"));
		}
	}

	public void filterStreet(Criteria criteria, String streetName) {
		if (isFilled(localityCode)) {
			criteria.add(Restrictions.like("code", localityCode.substring(0, 8) + "_________"));
		} else if (isFilled(regionCode)) {
			criteria.add(Restrictions.like("code", regionCode.substring(0, 5) + "___" + "_________"));
		} else if (isFilled(subjectCode)) {
			criteria.add(Restrictions.like("code", subjectCode.substring(0, 2) + "___" + "___" + "_________"));
		}
		if (streetName != null && streetName.length() > 0) {
			criteria.add(Restrictions.like("name", "%" + streetName + "%"));
		}
	}

//	public void filterHousing(Criteria criteria, String housing) {
//		if (isFilled(localityCode)) {
//			criteria.add(Restrictions.like("code", localityCode.substring(0, 8) + "%"));
//		} else if (isFilled(regionCode)) {
//			criteria.add(Restrictions.like("code", regionCode.substring(0, 5) + "%"));
//		} else if (isFilled(subjectCode)) {
//			criteria.add(Restrictions.like("code", subjectCode.substring(0, 2) + "%"));
//		}
//		if (housing != null && housing.length() > 0) {
//			criteria.add(Restrictions.like("korp", "%" + housing + "%"));
//		}
//	}

}
