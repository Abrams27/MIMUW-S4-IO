package pl.io.lekizapteki.repositories.columnTypes;

import lombok.Builder;
import pl.io.lekizapteki.services.Medicine;

@Builder
public class PackageSetter implements MedicinePropertySetter {
  private Medicine medicine;
  public void setMedicineProperty(String value) {
    medicine.setPack(value);
  }
}