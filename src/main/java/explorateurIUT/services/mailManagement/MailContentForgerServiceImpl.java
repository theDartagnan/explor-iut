package explorateurIUT.services.mailManagement;

import explorateurIUT.model.IUTRepository;
import explorateurIUT.model.MailIUTRecipient;
import explorateurIUT.model.projections.DepartementCodesOfIUTId;
import explorateurIUT.model.projections.IUTMailOnly;
import explorateurIUT.model.DepartementRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class MailContentForgerServiceImpl implements MailContentForgerService {

    private final static String NEW_LINE_MAIL = "\r\n";

    private final DepartementRepository deptRepo;

    private final IUTRepository iutRepo;

    @Autowired
    public MailContentForgerServiceImpl(DepartementRepository deptRepo, IUTRepository iutRepo) {
        this.deptRepo = deptRepo;
        this.iutRepo = iutRepo;
    }

    private void createExtraInformation(MailSendingRequest mailSendingRequest, StringBuilder finalBody) {
        finalBody.append("Identité : ")
                .append(mailSendingRequest.contactIdentity())
                .append(NEW_LINE_MAIL)
                .append("Nom entreprise : ")
                .append(mailSendingRequest.contactCompany())
                .append(NEW_LINE_MAIL)
                .append("Fonction dans l'entreprise : ")
                .append(mailSendingRequest.contactFunction())
                .append(NEW_LINE_MAIL)
                .append("Mail du contact : ")
                .append(mailSendingRequest.contactMail());
    }

    @Override
    public String createBody(MailSendingRequest mailSendingRequest) {
        final StringBuilder finalBody = new StringBuilder(mailSendingRequest.body());
        finalBody.append(NEW_LINE_MAIL)
                .append("Merci de transmettre cette demande au service compétent au sein de votre IUT et dans l’attente d’un retour rapide,")
                .append(NEW_LINE_MAIL).append("-".repeat(10)).append("\n"); //Add a separator between the body and the informations
        this.createExtraInformation(mailSendingRequest, finalBody);
        return finalBody.toString();
    }

    @Override
    public List<MailIUTRecipient> createIUTMailingList(MailSendingRequest mailSendingRequest) {
        // Extract iutId related to deptId : the request ensure uniqueness of iutId
        List<DepartementCodesOfIUTId> codesDeptByIUT = this.deptRepo.streamIUTIdByIdIn(mailSendingRequest.deptIds())
                .toList();
        // Extract mail only of iut from Id. The request ensures uniqueness of mailId
        List<String> iutIds = codesDeptByIUT.stream().map(DepartementCodesOfIUTId::getIut).toList();
        final Map<String, String> mailsByIUTid = this.iutRepo.streamMailOnlyByIdInAndMelIsNotNull(iutIds)
                .collect(Collectors.toMap(IUTMailOnly::getId, IUTMailOnly::getMel));
        return codesDeptByIUT.stream()
                .filter((dep)->mailsByIUTid.containsKey(dep.getIut()))
                .map((dep)->new MailIUTRecipient(mailsByIUTid.get(dep.getIut()),dep.getCodes()))
                .toList();
    }

    @Override
    public String createConfirmationMailSubject() {
        return "Explor'IUT - Confirmation de la recherche d'alternance";
    }

    @Override
    public String createConfirmationMailBody(String contactIdentity, String confirmationUrl) {
        StringBuilder sb = new StringBuilder("Bonjour");
        sb.append(contactIdentity).append(",").append(NEW_LINE_MAIL)
                .append("Suite à votre demande de contact avec les IUT de France.").append(NEW_LINE_MAIL)
                .append("Vous devez confirmer votre envoi en cliquant sur ce lien : ")
                .append(confirmationUrl).append(".").append(NEW_LINE_MAIL).append(NEW_LINE_MAIL)
                .append("Cordialement,").append(NEW_LINE_MAIL).append("Explor'IUT");
        return sb.toString();
    }

}
