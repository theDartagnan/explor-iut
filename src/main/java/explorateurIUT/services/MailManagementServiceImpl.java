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

import explorateurIUT.services.mailManagement.MailQuotaService;
import explorateurIUT.services.mailManagement.MailSendingRequest;
import jakarta.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author Remi Venant
 */
@Service
@Validated
public class MailManagementServiceImpl implements MailManagementService {

    private static final Log LOG = LogFactory.getLog(MailManagementServiceImpl.class);
    @Override
    public LocalDateTime requestMailSending(MailSendingRequest sendingRequest) throws ValidationException {
        LOG.debug("requestMailSending: TO IMPLEMENT!");
        return LocalDateTime.now();
    }

    @Override
    public void resendConfirmationMail(LocalDateTime creationDatetime, String contactMail) throws ValidationException, NoSuchElementException {
        LOG.debug("resendConfirmationMail: TO IMPLEMENT!");
    }

    @Override
    public int removeOutdatedPendingMailRequest() {
        LOG.debug("removeOutdatedPendingMailRequest: TO IMPLEMENT!");
        return 0;
    }

    @Override
    public void confirmMailSendingRequest(String confirmationToken) throws ValidationException, NoSuchElementException {
        LOG.debug("confirmMailSendingRequest: TO IMPLEMENT!");
    }

    @Override
    public boolean confirmTotalQuota(){
        return MailQuotaService.maxRequestAll();
    };

   @Override 
   public boolean confirmIPQuota(@NotBlank String clientIP) throws ValidationException;

   @Override
   public boolean contentValidation();

}
