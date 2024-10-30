package com.dats.datsconnexion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dats.datsconnexion.Repositories.ColumnsRepo;
import com.dats.datsconnexion.Services.ColumnsServiceImpl;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;


    public class ColumnServiceTest {

        @Mock
        private ColumnsRepo columnsRepo;

        @InjectMocks
        private ColumnsServiceImpl columnsService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testAddColumn() {
            // Arrange
            Columns column = new Columns();
            column.setType("Type1");
            column.setTname("Table Name");

            Label label = new Label();
            label.setName("Label1");
            column.setLabel(label);  // Ensure relationship is set

            when(columnsRepo.save(column)).thenReturn(column);

            // Act
            Columns result = columnsService.addColumn(column);

            // Assert
            assertNotNull(result);
            assertEquals("Type1", result.getType());
            assertEquals("Label1", result.getLabel().getName());
            verify(columnsRepo, times(1)).save(column);
        }

        @Test
        public void testUpdateColumn_Success() {
            // Arrange
            Long columnId = 1L;

            // Existing column with an old label
            Columns existingColumn = new Columns();
            existingColumn.setId(columnId);
            existingColumn.setType("OldType");
            existingColumn.setTname("OldTable");

            Label oldLabel = new Label();
            oldLabel.setName("OldLabel");
            existingColumn.setLabel(oldLabel);

            // New column with updated label and type
            Columns updatedColumn = new Columns();
            updatedColumn.setType("NewType");
            updatedColumn.setTname("NewTable");

            Label newLabel = new Label();
            newLabel.setName("NewLabel");
            updatedColumn.setLabel(newLabel);

            when(columnsRepo.findById(columnId)).thenReturn(Optional.of(existingColumn));
            when(columnsRepo.save(existingColumn)).thenReturn(existingColumn);

            // Act
            columnsService.updateColumn(columnId, updatedColumn);


            // Use ArgumentCaptor to capture the column saved
            ArgumentCaptor<Columns> captor = ArgumentCaptor.forClass(Columns.class);
            verify(columnsRepo).save(captor.capture());

            Columns savedColumn = captor.getValue();

            // Assert
            assertEquals("NewType", savedColumn.getType());
            assertEquals("NewTable", savedColumn.getTname());
            assertEquals("NewLabel", savedColumn.getLabel().getName());

            verify(columnsRepo, times(1)).findById(columnId);
            verify(columnsRepo, times(1)).save(existingColumn);
        }

        @Test
        public void testDeleteColumn_Success() {
            // Arrange
            Long columnId = 1L;

            // No need to set up any special behavior for deleteById, as it returns void.
            doNothing().when(columnsRepo).deleteById(columnId);

            // Act
            columnsService.deleteColumn(columnId);

            // Assert
            verify(columnsRepo, times(1)).deleteById(columnId);  // Ensure deleteById was called once with correct ID
        }

        @Test
        public void testUpdateColumn_ColumnNotFound() {
            // Arrange
            Long columnId = 1L;
            Columns updatedColumn = new Columns();

            when(columnsRepo.findById(columnId)).thenReturn(Optional.empty());

            // Act & Assert
            IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> columnsService.updateColumn(columnId, updatedColumn));
            assertEquals("cette colonne de stockage n'existe pas", exception.getMessage());

            verify(columnsRepo, times(1)).findById(columnId);
            verify(columnsRepo, never()).save(any(Columns.class));
        }
    }



