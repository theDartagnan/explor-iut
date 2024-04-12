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
package explorateurIUT.services;

import explorateurIUT.services.mailManagement.MailSendingRequest;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Point d'entrée de la gestion des envoies de courriel aux iuts.
 *
 * @author Remi Venant
 */
public interface MailManagementService {

    /**
     * Given a mail sending request, store it in pending requests, send a
     * confirmation link by mail to the contact. The create date returned can be
     * used after in conjonction with the contact mail to re-send the
     * confirmation mail
     *
     * @param sendingRequest the mail sending request
     * @return the creation datetime
     * @throws ValidationException if given parameters are invalid
     */
    LocalDateTime requestMailSending(@NotNull @Valid MailSendingRequest sendingRequest) throws ValidationException;

    /**
     * Re-send the confirmation mail related to a pending mail sending request,
     * if and only if the previous one has been sent more than 3 minutes ago
     *
     * @param creationDatetime the creation date
     * @param contactMail the contact mail address
     * @throws ValidationException if given parameters are invalid
     * @throws NoSuchElementException if no pending mail request matches
     * creationDatetime and contactMail
     */
    void resendConfirmationMail(@NotNull LocalDateTime creationDatetime, @NotNull @Email String contactMail) throws ValidationException, NoSuchElementException;

    /**
     * Remove all pending mail request that have been created more than 6 hours
     * ago
     *
     * @return
     */
    int removeOutdatedPendingMailRequest();

    /**
     * Validate a mail sending request based on its token sent through the
     * confirmation mail. If validated, the mail will be sent to iuts, and the
     * pending request removed.
     *
     * @param confirmationToken the mail sending request token.
     * @throws ValidationException if given parameters are invalid
     * @throws NoSuchElementException if no pending mail request matches the
     * confirmation token
     */
    void confirmMailSendingRequest(@NotBlank String confirmationToken) throws ValidationException, NoSuchElementException;

    /**
     * Check if the total quota of DB ask is reached
     * @return true if this quota isn't reached
     */
    boolean confirmTotalQuota();


    /**
     * Check if the client quota of DB ask is reached
     * @param clientIP IP of the client
     * @return true if this quota isn't reached
     * @throws ValidationException if given parameters are invalid
     */
    boolean confirmIPQuota(@NotBlank String clientIP) throws ValidationException;

    /**
     * Function who sanitize the mail
     * @return true if any error is find
     */
    boolean contentValidation();


}
