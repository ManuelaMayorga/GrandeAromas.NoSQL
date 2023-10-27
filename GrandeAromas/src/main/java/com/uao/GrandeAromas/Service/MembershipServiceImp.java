package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.MembershipModel;
import com.uao.GrandeAromas.Repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImp implements IMembershipService {
    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public String guardarMembership(MembershipModel membership) {
        membershipRepository.save(membership);
        return "Membership guardada";
    }

    @Override
    public String eliminarMembershipPorId(int membershipId) {
        if (membershipRepository.existsById(membershipId)) {
            membershipRepository.deleteById(membershipId);
            return "Membership con ID " + membershipId + " eliminada";
        } else {
            return "Membership no encontrada con el ID: " + membershipId;
        }
    }

    @Override
    public List<MembershipModel> obtenerMemberships() {
        return membershipRepository.findAll();
    }

    @Override
    public String actualizarMembershipPorId(int membershipId, MembershipModel membership) {
        Optional<MembershipModel> optionalMembership = membershipRepository.findById(membershipId);

        if (optionalMembership.isPresent()) {
            membership.setId(membershipId);
            membershipRepository.save(membership);
            return "Membership con ID " + membershipId + " actualizada";
        } else {
            return "Membership no encontrada con el ID: " + membershipId;
        }
    }

    @Override
    public Optional<MembershipModel> obtenerMembershipPorId(int membershipId) {
        return membershipRepository.findById(membershipId);
    }
}
