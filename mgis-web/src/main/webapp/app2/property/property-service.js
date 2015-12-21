angular.module("mgis.property.service", [])
	.factory("MGISPropertyRightsService", function () {
		return {
			buildDocumentsCertifyingRights: function (rights) {
				if (rights.documentsCertifyingRights && rights.documentsCertifyingRights.length) {
					return new Array().concat(rights.documentsCertifyingRights);
				}
				return null;
			},
			buildRegistrationDocuments: function (rights) {
				if (rights.registrationDocuments && rights.registrationDocuments.length) {
					return new Array().concat(rights.registrationDocuments);
				}
				return null;
			},
			buildOtherDocuments: function (rights) {
				if (rights.otherDocuments && rights.otherDocuments.length) {
					return new Array().concat(rights.otherDocuments);
				}
				return null;
			},
			buildRights: function (rights) {
				var rights2;
				if (rights) {
					rights2 = {
						ownershipForm: rights.ownershipForm ? {id: rights.ownershipForm.id} : null,
						rightKind: rights.rightKind ? {id: rights.rightKind.id} : null,
						rightOwner: rights.rightOwner ? {id: rights.rightOwner.id} : null,
						encumbrance: rights.encumbrance,
						obligations: rights.obligations,
						ownershipDate: rights.ownershipDate,
						terminationDate: rights.terminationDate,
						comment: rights.comment,
						share: rights.share,
						annualTax: rights.annualTax,
						totalArea: rights.totalArea,
						registrationDocuments: this.buildRegistrationDocuments(rights),
						documentsCertifyingRights: this.buildDocumentsCertifyingRights(rights),
						otherDocuments: this.buildOtherDocuments(rights)

					}
				} else {
					rights2 = {}
				}
				return rights2;
			}
		}
	})
;
