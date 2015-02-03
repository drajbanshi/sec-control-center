


<head>
<h1> Securitization Control Center Data from MBS</h1>
<link rel="stylesheet" href="\${resource(dir: 'css', file: 'scaffold.css')}"/>

</head>
<g:form action="index" method="post" >

  <div class="dialog">
    <table>
      <tbody>                                         
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityCUSIPIdentifier">Security CUSIPIdentifier :</label></td>
          
          <td valign="top" class="name"><label for="SecurityCUSIPIdentifier">${result.SecurityCUSIPIdentifier}</label></td>
            
        </tr> 
        <tr class="prop">
          <td valign="top" class="name"><label for="ActualMaturityDate">Actual Maturity Date :</label></td>
          <td valign="top" class="name"><label for="ActualMaturityDate">${result.ActualMaturityDate}</label></td>
        </tr>                                                 
    
     <tr class="prop">
          <td valign="top" class="name"><label for="AmortizationEndDate">Amortization End Date :</label></td>
         <td valign="top" class="name"><label for="ActualMaturityDate">${result.ActualMaturityDate}</label></td>
     </tr>   
    
     <tr class="prop">
          <td valign="top" class="name"><label for="AssetBackedSecurityType">Asset Backed Security Type :</label></td>
           <td valign="top" class="name"><label for="AssetBackedSecurityType">${result.AssetBackedSecurityType}</label></td>   
       </tr>   
    
     <tr class="prop">
          <td valign="top" class="name"><label for="BusinessCalendarType ">Business Calendar Type :</label></td>
           <td valign="top" class="name"><label for="BusinessCalendarType">${result.BusinessCalendarType}</label></td>    
      </tr>    
    
    <tr class="prop">
          <td valign="top" class="name"><label for="ContractualMaturityDate ">Contractual Maturity Date :</label></td>
          <td valign="top" class="name"><label for="ContractualMaturityDate">${result.ContractualMaturityDate}</label></td> 
     </tr>   
      <tr class="prop">
          <td valign="top" class="name"><label for="CurrencyType  ">Currency Type  :</label></td>
           <td valign="top" class="name"><label for="CurrencyType">${result.CurrencyType}</label></td>    
        </tr>  
        
          <tr class="prop">
          <td valign="top" class="name"><label for="FederalReserveSecurityClassCode  ">Federal Reserve Security Class Code :</label></td>
           <td valign="top" class="name"><label for="FederalReserveSecurityClassCode">${result.FederalReserveSecurityClassCode}</label></td>  
        </tr>  
        <tr class="prop">
          <td valign="top" class="name"><label for="FederalReserveSecurityDescription ">Federal Reserve Security Description :</label></td>
           <td valign="top" class="name"><label for="FederalReserveSecurityDescription">${result.FederalReserveSecurityDescription}</label></td>    
        </tr>  
          <tr class="prop">
          <td valign="top" class="name"><label for="InternationalSecurityIdentifier  ">International Security Identifier :</label></td>
           <td valign="top" class="name"><label for="InternationalSecurityIdentifier ">${result.InternationalSecurityIdentifier }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="MaturityReasonType  ">MaturityReasonType :</label></td>
           <td valign="top" class="name"><label for="MaturityReasonType ">${result.MaturityReasonType }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="MinimumTradeDenominationAmount  ">Minimum Trade Denomination Amount :</label></td>
           <td valign="top" class="name"><label for="MinimumTradeDenominationAmount ">${result.MinimumTradeDenominationAmount  }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="MinimumTradeIncrementAmount  ">Minimum Trade Increment Amount :</label></td>
           <td valign="top" class="name"><label for="MinimumTradeIncrementAmount ">${result.MinimumTradeIncrementAmount }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="PaymentFrequencyType  ">Federal Payment Frequency Type :</label></td>
           <td valign="top" class="name"><label for="PaymentFrequencyType ">${result.PaymentFrequencyType }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="PrincipalAmortizationType  ">PrincipalAmortizationType :</label></td>
           <td valign="top" class="name"><label for="PrincipalAmortizationType ">${result.PrincipalAmortizationType}</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="RegistrationAgentIdentifier  ">RegistrationAgentIdentifier :</label></td>
           <td valign="top" class="name"><label for="RegistrationAgentIdentifier ">${result.RegistrationAgentIdentifier }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="SecuritizationProgramIdentifier  ">Securitization Program Identifier :</label></td>
           <td valign="top" class="name"><label for="SecuritizationProgramIdentifier ">${result.SecuritizationProgramIdentifier }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="SecurityBusinessDayAdjustmentType  ">Security BusinessDay Adjustment Type :</label></td>
           <td valign="top" class="name"><label for="SecurityBusinessDayAdjustmentType ">${result.SecurityBusinessDayAdjustmentType }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="SecurityCouponType  ">SecurityCouponType :</label></td>
           <td valign="top" class="name"><label for="SecurityCouponType ">${result.SecurityCouponType }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="SecurityCouponTypeConversionDate  ">Security CouponType Conversion Date :</label></td>
           <td valign="top" class="name"><label for="SecurityCouponTypeConversionDate ">${result.SecurityCouponTypeConversionDate }</label></td>    
        </tr> 
         <tr class="prop">
          <td valign="top" class="name"><label for="SecurityFirstInterestAccrualDate  ">Security First Interest Accrual Date :</label></td>
           <td valign="top" class="name"><label for="SecurityFirstInterestAccrualDate ">${result.SecurityFirstInterestAccrualDate }</label></td>    
        </tr> 
       <tr class="prop">
          <td valign="top" class="name"><label for="SecurityFirstPaymentDate   ">Security First Payment Date :</label></td>
           <td valign="top" class="name"><label for="SecurityFirstPaymentDate  ">${result.SecurityFirstPaymentDate  }</label></td>    
        </tr>   
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityGuarantyContractIdentifier   ">Security Guaranty Contract Identifier :</label></td>
           <td valign="top" class="name"><label for="SecurityGuarantyContractIdentifier  ">${result.SecurityGuarantyContractIdentifier  }</label></td>    
        </tr>   
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityFirstInterestAccrualDate  ">Security First Interest Accrual Date :</label></td>
           <td valign="top" class="name"><label for="SecurityFirstInterestAccrualDate ">${result.SecurityFirstInterestAccrualDate }</label></td>    
        </tr>   
         <tr class="prop">
          <td valign="top" class="name"><label for="SecurityIssuerIdentifier   ">Security Issuer Identifier :</label></td>
           <td valign="top" class="name"><label for="SecurityIssuerIdentifier  ">${result.SecurityIssuerIdentifier  }</label></td>    
        </tr>  
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityMaturityTermMonthCount   ">Security Maturity Term Month Count :</label></td>
           <td valign="top" class="name"><label for="SecurityMaturityTermMonthCount  ">${result.SecurityMaturityTermMonthCount  }</label></td>    
        </tr>  <tr class="prop">
          <td valign="top" class="name"><label for="SecurityMaturityType   ">SecurityMaturityType :</label></td>
           <td valign="top" class="name"><label for="SecurityMaturityType  ">${result.SecurityMaturityType  }</label></td>    
        </tr>   
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityNotionalIndicator   ">SecurityNotionalIndicator  :</label></td>
           <td valign="top" class="name"><label for="SecurityNotionalIndicator  ">${result.SecurityNotionalIndicator}</label></td>    
        </tr>    
        <tr class="prop">
          <td valign="top" class="name"><label for="SecurityOwnershipMethodType   ">Security Ownership Method Type :</label></td>
           <td valign="top" class="name"><label for="SecurityOwnershipMethodType  ">${result.SecurityOwnershipMethodType  }</label></td>    
        </tr>      
       <tr class="prop">
          <td valign="top" class="name"><label for="SecurityPayingAgentIdentifier   ">Security Paying Agent Identifier :</label></td>
           <td valign="top" class="name"><label for="SecurityPayingAgentIdentifier  ">${result.SecurityPayingAgentIdentifier  }</label></td>    
        </tr>     
         
         
         
      </tbody>
    </table>
    
    <br>
  </div>
  <div class="buttons">
    <span class="button"><input class="submit" type="submit" value="Search" /></span>
  </div>
</g:form>