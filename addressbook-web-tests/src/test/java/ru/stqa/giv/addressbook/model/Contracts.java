package ru.stqa.giv.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contracts extends ForwardingSet<ContractData>{

        private Set<ContractData> delegate;

        public Contracts(Contracts contracts) {
            this.delegate = new HashSet<ContractData>(contracts.delegate);
        }

    public Contracts(Collection<ContractData> contracts) {
        this.delegate = new HashSet<ContractData>(contracts);
    }


        @Override
        protected Set<ContractData> delegate() {
            return delegate;
        }

        public Contracts(){
            this.delegate = new HashSet<ContractData>();
        }

        public Contracts withAdded(ContractData contract){
            Contracts contracts = new Contracts(this);
            contracts.add(contract);
            return contracts;
        }

    public Contracts withOut(ContractData contract){
        Contracts contracts = new Contracts(this);
        contracts.remove(contract);
        return contracts;
    }





}
