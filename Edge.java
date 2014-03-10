//package com.chaoticity.dependensee;

import java.io.Serializable;

/*
acomp:     adjectival complement
advcl:     adverbial clause modifier
advmod:    adverbial modifier
agent:     agent
amod:      adjectival modifier
appos:     appositional modifier
aux:       auxiliary
cc:        coordination
ccomp:     clausal complement
conj:      conjunct
cop:       copula
csubj:     clausal subject
csubjpass: clausal passive subject
dep:       dependent
det:       determiner
discourse: discourse element
dobj:      direct object
expl:      expletive
goeswith:  goes with
iobj:      indirect object
mark:      marker
mwe:       multi-word expression
neg:       negation modifer
nn:        noun compound modifier
npadvmod:  noun phrase as adverbial modifier
nsubj:     nominal subject
nsubjpass: passive nominal subject
num:       numeric modifier
number:    element of compound number
parataxis: parataxis
pcomp:     prepositional complement
pobj:      object of a preposition
poss:      possession modifier
possessive: possesive modifier
preconj:    preconjunct
predet:     predeterminer
prep:       prepositional modifier
prepc:      prepositional clausal modifer
prt:        phrasal verb particle
punct:      punctuation
quantmod:   quatifier phrase modifier
ref:        referent
root:       root
tmod:       temporal modifier
vmod:       reduced non-finite verbal modifier
xcomp:      open clausal complement
xsubj:      controlling subject


*/

public class Edge implements Serializable{

    public Node source;
    public Node target;
    public String label;
    public int sourceIndex;
    public int targetIndex;
    public boolean visible = false;
    public int height;

    @Override
    public String toString() {
	return label+"["+sourceIndex+"->" + targetIndex+"]";
    }


}
