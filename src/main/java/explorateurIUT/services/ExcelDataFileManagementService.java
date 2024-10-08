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

import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Rémi Venant
 */
public interface ExcelDataFileManagementService {

    String DEFAULT_FILE_EXTENSION = "xlsx";

    List<? extends DataFileHistoryEntry> getHistory() throws IOException;

    Path getCurrentFilePath() throws IOException, NoSuchElementException;

    Path getFilePath(@NotNull LocalDateTime version) throws IOException, NoSuchElementException;

    ExcelChangeSession getChangeExcelSession() throws IOException;

    interface DataFileHistoryEntry {

        LocalDateTime getVersion();

        boolean isUsed();
    }

    interface ExcelChangeSession {

        void applyChange(InputStream dataExcel) throws IOException, SecurityException;

        void commit();

        void rollback() throws IOException, SecurityException;
    }
}
