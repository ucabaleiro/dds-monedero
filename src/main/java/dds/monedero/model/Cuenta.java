package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
  private double saldo = 0;
  private List<Movimiento> movimientos = new ArrayList<>();
  public Cuenta() {
    saldo = 0;
  }
  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }
  public void poner(double cuanto) {
    validarMonto(cuanto);
    validarDepositosdiarios();
    new Movimiento(LocalDate.now(), cuanto, true).agregateA(this);
  }

  private void validarDepositosdiarios() {
    if (getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
  }
  public void sacar(double cuanto) {
    validarMonto(cuanto);
    validarSaldoSuficiente(cuanto);
    validarLimiteDiario(cuanto);
    new Movimiento(LocalDate.now(), cuanto, false).agregateA(this);
  }

  private void validarSaldoSuficiente(double monto) {
    if (getSaldo() - monto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
    }
  }

  private void validarLimiteDiario(double monto) {
    double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    double limite = 1000 - montoExtraidoHoy;
    if (monto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limite);
    }
  }

  private void validarMonto(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
  }

  public void agregarMovimiento(LocalDate fecha, double cuanto, boolean esDeposito) {
    Movimiento movimiento = new Movimiento(fecha, cuanto, esDeposito);
    movimientos.add(movimiento);
  }
  public double getMontoExtraidoA(LocalDate fecha) {
    return getMovimientos().stream()
        .filter(movimiento -> !movimiento.isDeposito() && movimiento.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }
  public List<Movimiento> getMovimientos() {
    return movimientos;
  }
  public double getSaldo() {
    return saldo;
  }
  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
