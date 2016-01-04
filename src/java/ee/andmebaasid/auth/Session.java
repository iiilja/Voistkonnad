/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.auth;

import ee.andmebaasid.entity.VTootaja;

/**
 *
 * @author iljad
 */
public class Session {
    private final VTootaja tootaja;

    public Session(VTootaja tootaja) {
        this.tootaja = tootaja;
    }

    public VTootaja getTootaja() {
        return tootaja;
    }
}
