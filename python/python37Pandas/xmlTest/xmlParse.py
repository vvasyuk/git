import xml.etree.ElementTree as Et

xml = """<?xml version='1.0' encoding='UTF-8'?>
<response success="false">
  <messages>
    <message type="ERROR">The level PSS07509 cannot be moved to another parent while proceedWithWarnings = 0.</message>
    <message type="ERROR">The level FBI00008 cannot be moved to another parent while proceedWithWarnings = 0.</message>
    <message type="ERROR">The level NAV00380 cannot be moved to another parent while proceedWithWarnings = 0.</message>
    <message type="ERROR">The level ALA07550 cannot be moved to another parent while proceedWithWarnings = 0.</message>
  </messages>
  <output>
    <levels>
      <level id="49" name="DOJ JUSTICE" currency="USD" shortName="" eliminationLevel="0" eliminationTradingPartner="0" inWorkflow="1">
        <level id="" name="MARSHALS SERVICE, UNITED" currency="USD" shortName="" eliminationLevel="0" eliminationTradingPartner="0" inWorkflow="1">
          <level id="" name="MAR00204" currency="USD" shortName="" eliminationLevel="0" eliminationTradingPartner="0" inWorkflow="1">
            <level id="" name="MAR00204.00" currency="USD" shortName="" eliminationLevel="0" eliminationTradingPartner="0" inWorkflow="1" />
          </level>
        </level>
      </level>
    </levels>
  </output>
</response>"""

root = Et.fromstring(xml)
result = {}
result['error'] = []
for msg in root.findall(".//messages/"):
    result['error'].append(msg.text)
    print(msg.text)

print('loop')
for err in result['error']:
    print(err)