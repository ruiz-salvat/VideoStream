export default function Video({ videoDetails }) {
   return <>
      <p>Post: {videoDetails.title}</p>
   </>
}
 
export async function getStaticPaths() {
   const videosRes = await fetch('http://127.0.0.1:8080/video/all')
   const videos = await videosRes.json()

   const paths = videos.map((video) => ({
      params: { slug: video.slug },
   }))

   return { paths, fallback: false }
}
 
export async function getStaticProps({ params }) {
   const videoDetailsRes = await fetch(`http://127.0.0.1:8080/video/details/${params.slug}`)
   const videoDetails = await videoDetailsRes.json()

   return {
      props: {
         videoDetails
      }
   }
}