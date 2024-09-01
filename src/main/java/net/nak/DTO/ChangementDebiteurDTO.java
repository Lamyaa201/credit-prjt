package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitParticulier;
import net.nak.enums.Debiteur;
import java.util.Date;
import java.util.List;

@Setter @Getter
public class ChangementDebiteurDTO extends AnnexeDTO {
        private String numCredit;
        private String numCIN;
        private Debiteur debiteurInit;
        private Debiteur nouveauDebit;
        private Date dateEffetTransfert;

    private Boolean isActive = true;

}

