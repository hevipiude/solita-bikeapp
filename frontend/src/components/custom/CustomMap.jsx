import { Map, Marker, ZoomControl } from 'pigeon-maps'

function CustomMap({ y, x }) {
  const position = [y, x]
  const color = '#e57373'

  return (
    <Map
      sx={{ width: '100%' }}
      height={280}
      defaultCenter={position}
      defaultZoom={16}>
      <ZoomControl />
      <Marker color={color} width={40} anchor={position} />
    </Map>
  )
}

export default CustomMap
