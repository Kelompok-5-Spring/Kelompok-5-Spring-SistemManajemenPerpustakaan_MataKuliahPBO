/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemmanajemenperpustakaankampus;

import service.PerpustakaanService;
import ui.MenuPerpustakaan;
/**
 *
 * @author ASUS
 */
public class SistemManajemenPerpustakaanKampus {

    public static void main(String[] args) {
        PerpustakaanService service = new PerpustakaanService();
        MenuPerpustakaan menu = new MenuPerpustakaan(service);
        menu.jalankan();
    }
}
