package com.exozonia.domo.util;

import com.exozonia.domo.model.Avatar;
import lombok.Data;

@Data
public class AvatarUtils {
    //EXPEMPLO UNIDADES DE DISTANCIA
    private static final double LIMITE_AUDICAO_PADRAO =10.0;
    public static double calcularDistancia(Avatar a1 , Avatar a2){
        if(a1== null || a2 ==null) return Double.MAX_VALUE;
        return Math.sqrt(Math.pow(a1.getPosX() - a2.getPosX(),2)+
                Math.pow(a1.getPosY()- a2.getPosY(),2));
    }
public static boolean podeOuvir(Avatar a1,Avatar a2){
        return calcularDistancia(a1,a2)<=LIMITE_AUDICAO_PADRAO;
}

public static boolean podeOuvir(Avatar a1 , Avatar a2 ,double limiteAudicao){
        return calcularDistancia(a1,a2)<= limiteAudicao;
}





}
