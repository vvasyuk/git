```mermaid
graph TD
    Wb(web-book); 
    An(annuario); 
    Ss[schede<br>stazioni]; 
    Si[schede<br>indicatori]; 
    G[grafici]; 
    Ar[archivio]; 
    Pr[prodotti]
    subgraph;
        Ss;
        Si;
        G;
        Ar;
    end
    subgraph;
        Wb;
        An;
        Pr;
    end
    Ar ==> Pr
    classDef label fill:#eef, stroke:#eef
    class Ar,Pr label
```