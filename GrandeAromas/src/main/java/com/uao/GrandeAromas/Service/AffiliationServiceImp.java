package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
import com.uao.GrandeAromas.Model.AffiliationModel;
import com.uao.GrandeAromas.Repository.AffiliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AffiliationServiceImp implements IAffiliationService {
    @Autowired
    private AffiliationRepository affiliationRepository;

    @Override
    public String guardarAffiliation(AffiliationModel affiliation) {
        affiliationRepository.save(affiliation);
        return "Affiliation guardada";
    }

    @Override
    public String eliminarAfiliacionPorId(int affiliationId) {
        if (affiliationRepository.existsById(affiliationId)) {
            affiliationRepository.deleteById(affiliationId);
            return "Affiliation con ID " + affiliationId + " eliminada";
        } else {
            throw new RecursoNoEncontradoException("Affiliation no encontrada con el ID: " + affiliationId);
        }
    }

    @Override
    public List<AffiliationModel> obtenerAffiliation() {
        return affiliationRepository.findAll();
    }

    @Override
    public String actualizarAfiliacionPorId(int affiliationId) {
        Optional<AffiliationModel> optionalAffiliation = affiliationRepository.findById(affiliationId);

        if (!optionalAffiliation.isPresent()) {
            throw new RecursoNoEncontradoException("Affiliation no encontrada con el ID: " + affiliationId);
        }

        affiliationRepository.save(optionalAffiliation.get());
        return "Affiliation con ID " + affiliationId + " actualizada";
    }

    @Override
    public Optional<AffiliationModel> obtenerAffiliationPorId(int affiliationId) {
        return affiliationRepository.findById(affiliationId);
    }

    @Override
    public String actualizarAfiliacion(AffiliationModel affiliation) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizarAfiliacion'");
    }
}

