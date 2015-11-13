/**
 * ProtocolGIMP
 *
 * Copyright (C) 2012 Sh1fT
 *
 * This file is part of Serveur_Credit.
 *
 * Serveur_Credit is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * Serveur_Credit is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Serveur_Credit; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package protocols;

import serveur_credit.Serveur_Credit;

/**
 * Manage a {@link ProtocolGIMP}
 * @author Sh1fT
 */
public class ProtocolGIMP implements interfaces.ProtocolGIMP {
    private Serveur_Credit parent;
    public static final int RESPONSE_OK = 100;
    public static final int RESPONSE_KO = 600;

    /**
     * Create a new {@link ProtocolGIMP} instance
     * @param parent 
     */
    public ProtocolGIMP(Serveur_Credit parent) {
        this.setParent(parent);
    }

    public Serveur_Credit getParent() {
        return parent;
    }

    public void setParent(Serveur_Credit parent) {
        this.parent = parent;
    }

    /**
     * Vérifie une carte de crédit
     * @param cardNumber
     * @return 
     */
    @Override
    public Integer verifyCardNumber(String cardNumber) {
        int sum = 0;
 
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0) ? ProtocolGIMP.RESPONSE_OK :
                ProtocolGIMP.RESPONSE_KO;
    }
}