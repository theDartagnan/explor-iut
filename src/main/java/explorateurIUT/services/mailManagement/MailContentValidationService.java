/*
 * Copyright (C) 2024 IUT Laval - Le Mans Université.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package explorateurIUT.services.mailManagement;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 *
 * @author Remi Venant
 */
public interface MailContentValidationService {

    /**
     * Check if the mail informations are valid
     *
     * @param mailContent: any content included in the mail
     * @return true if the sanitizer detect nothing
     * @throws ValidationException if given parameters are invalid
     */
    boolean isValid(@NotBlank String mailContent) throws ValidationException;

    /**
     * Check if attachements match the different limit (number, total size, size per attachement)
     * @param attachements
     * @return
     * @throws ValidationException 
     */
    boolean isValid(List<MailRequestAttachement> attachements) throws ValidationException;
}
