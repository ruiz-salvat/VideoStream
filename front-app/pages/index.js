import Link from 'next/link'

export default function HomePage({ categories, videos }) {
   return (
      <div>
         <ul>
            {categories.map((category) => (
               <li key={category.id}>{category.name}</li>
            ))}
         </ul>
         
         <ul>
            {videos.map((video) => (
               <li key={video.slug}>{video.title}</li>
            ))}
         </ul>
      </div>
    )
}

export async function getStaticProps() {
   const categoriesRes = await fetch('http://127.0.0.1:8080/category/all')
   const videosRes = await fetch('http://127.0.0.1:8080/video/all')
   const categories = await categoriesRes.json()
   const videos = await videosRes.json()

   return {
      props: {
         categories,
         videos,
      }
   }
}