package com.exozonia.domo.model;
import com.exozonia.domo.enums.AmmoType;
import com.exozonia.domo.enums.WeaponClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Weapon")


public class Weapon {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private WeaponClass weaponClass;
    private AmmoType  ammoType;
    private int damagePerShot;
    private Float fireRate;
    private boolean isLegendary;
    private String description;
    @Relationship(type = "TEM_SKIN", direction = Relationship.Direction.OUTGOING)
    private List<Skin> skins;
}
