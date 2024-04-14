package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.CryptoTypeDTO;
import com.aquariux.cryptoapi.dto.WalletDTO;
import com.aquariux.cryptoapi.dto.WalletResponse;
import com.aquariux.cryptoapi.entity.CryptoType;
import com.aquariux.cryptoapi.entity.Wallet;
import com.aquariux.cryptoapi.repository.CryptoTypeRepository;
import com.aquariux.cryptoapi.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CryptoTypeRepository cryptoTypeRepository;

    private final ModelMapper mapper;

    public WalletResponse getWalletDetail(long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow();
        List<CryptoType> cryptoTypes = cryptoTypeRepository.findByWallet_id(id);
        WalletDTO walletDTO = maptoWalletDTO(wallet);
        List<CryptoTypeDTO> cryptoTypeDTOs = cryptoTypes.stream()
                                .map(c->mapper.map(c, CryptoTypeDTO.class))
                                .collect(Collectors.toList());
        WalletResponse walletResponse = WalletResponse.builder()
                .wallet(walletDTO)
                .cryptoType(cryptoTypeDTOs)
                .build();
        return walletResponse;
    }

    private WalletDTO maptoWalletDTO(Wallet wallet) {
        WalletDTO walletDTO =mapper.map(wallet, WalletDTO.class);
        return  walletDTO;
    }


}
