package com.uao.GrandeAromas.Service;

import java.util.List;
import java.util.Optional;
import com.uao.GrandeAromas.Model.AffiliationModel;

public interface IAffiliationService {
    String guardarAffiliation(AffiliationModel affiliation);

    List<AffiliationModel> obtenerAffiliation();

    String eliminarAfiliacionPorId(int affiliationId);

    String actualizarAfiliacionPorId(int affiliationId);

    String actualizarAfiliacion(AffiliationModel affiliation);

    Optional<AffiliationModel> obtenerAffiliationPorId(int affiliationId);
}

