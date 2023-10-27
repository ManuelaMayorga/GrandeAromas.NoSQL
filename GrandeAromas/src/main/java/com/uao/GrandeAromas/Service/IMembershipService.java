package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.MembershipModel;
import java.util.List;
import java.util.Optional;

public interface IMembershipService {
    String guardarMembership(MembershipModel membership);
    List<MembershipModel> obtenerMemberships();
    String eliminarMembershipPorId(int membershipId);
    String actualizarMembershipPorId(int membershipId, MembershipModel membership);
    Optional<MembershipModel> obtenerMembershipPorId(int membershipId);
}

