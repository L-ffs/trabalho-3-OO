package com.sistema.franquia;

import java.util.ArrayList;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.*;

public class Franquia {

    private Dono dono;
    private ArrayList<Filial> filiais = new ArrayList<>();
    
    
    public Franquia() {};
    

    
    
    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public ArrayList<Filial> getFiliais() {
        return filiais;
    }
    public void setFiliais(ArrayList<Filial> filiais) {
        this.filiais = filiais;
    }
     

    
}
