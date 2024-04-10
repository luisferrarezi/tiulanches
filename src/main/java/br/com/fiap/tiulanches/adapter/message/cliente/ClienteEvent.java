package br.com.fiap.tiulanches.adapter.message.cliente;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEvent {
    private EventoEnum evento;
    private ClienteDto clienteDto;
}
